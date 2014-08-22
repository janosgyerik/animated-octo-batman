package com.janosgyerik.stackoverflow.junk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public final class FastReader {

    private final BufferedReader bufferedReader;
    private StringTokenizer tokenizer;

    private FastReader(final BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        this.tokenizer = null;
    }

    public static final FastReader from(final InputStream inputStream) {
        return new FastReader(new BufferedReader(new InputStreamReader(inputStream)));
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line;
            try {
                if ((line = bufferedReader.readLine()) == null) return null;
            } catch (IOException unexpected) {
                throw new RuntimeException(unexpected);
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public BigDecimal nextBigDecimal() {
        return new BigDecimal(next());
    }

    public BigInteger nextBigInteger() {
        return new BigInteger(next());
    }

    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException unexpected) {
            throw new RuntimeException(unexpected);
        }
    }
}
