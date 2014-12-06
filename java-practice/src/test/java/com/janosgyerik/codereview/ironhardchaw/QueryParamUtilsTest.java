package com.janosgyerik.codereview.ironhardchaw;

import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

class QueryParamUtils {
    private static final Pattern PATTERN = Pattern.compile("([^\\[\\]\\.]+)");

    public static Map<String, Object> parse_str(String str) {
        final String pairs[] = str.split("&");
        final OurMap result = new OurMap();
        if (pairs.length == 0) {
            return result;
        }

        for (String pair : pairs) {
            final String[] tmp = pair.split("=", 2);
            final String key = tmp[0];
            final String[] path = keyToPath(key);
            final String value = tmp.length > 1 ? tmp[1] : "";

            // Now iterate over the key path until we're done
            OurMap map = result;
            for (int i = 0; i < path.length; i++) {
                final String part = path[i];
                // If no element exists for this part, we can add it straight
                // away
                if (!map.containsKey(part)) {
                    // This is the end of the key path, so just put the value
                    if (i >= path.length - 1) {
                        map.put(part, value);
                    } else {
                        OurMap m = new OurMap();
                        map.put(part, m);
                        map = m;
                    }
                }

                // An element already exists, so we have to check to see what it
                // is, see if we need to do any conversions, etc.
                else {
                    Object current = map.get(part);
                    // If it's already a map, we can continue on down the path
                    if (current instanceof OurMap) {
                        if (i >= path.length - 1) {
                            ((OurMap) current).put(part, value);
                        } else {
                            map = (OurMap) current;
                        }
                    }
                    // If it's a scalar object, we need to convert it to a list
                    // (in map form for weird indices)
                    else {
                        OurMap m = new OurMap();
                        m.add(current);
                        if (i >= path.length) {
                            m.add(value);
                        }
                        m.add(value);
                        map.put(part, m);
                        map = m;
                    }
                }
            }
        }
        return result;
    }

    protected static String[] keyToPath(final String key) {
        final Matcher matcher = PATTERN.matcher(key);
        final List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result.toArray(new String[result.size()]);
    }

    public static class OurMap extends HashMap<String, Object> {
        public void add(final Object obj) {
            System.out.println("x");
            put(String.valueOf(keySet().size()), obj);
        }
    }
}

public class QueryParamUtilsTest {
    @Test
    public void test() {
        assertEquals("{firstName=Jane, lastName=Smith, organizations={0={addresses={street=Wentworth%20Dr\n}, id=1234}}}",
                QueryParamUtils.parse_str("firstName=Jane&lastName=Smith&organizations[0][id]=1234&organizations[0].addresses[].street=Wentworth%20Dr\n").toString());
    }
}
