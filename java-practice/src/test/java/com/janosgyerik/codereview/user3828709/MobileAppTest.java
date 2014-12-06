//package com.janosgyerik.stackoverflow.user3828709;
//
//final class MyScreen extends MainScreen {
//
//    protected static boolean pageLoaded = false;
//    private String targetURL = "http://reelafrica.net";
//
//
//    public MyScreen() {
//        createGUI();
//    }
//
//    private void createGUI() {
//
//        VerticalFieldManager mainManager = new VerticalFieldManager(Field.USE_ALL_WIDTH | Field.USE_ALL_HEIGHT | Manager.VERTICAL_SCROLLBAR | Manager.HORIZONTAL_SCROLLBAR);
//        BrowserField browserField = createBrowserField();
//
//        mainManager.add(browserField);
//
//        add(mainManager);
//
//
//    }
//
//    private boolean checkURL(String mURL) {
//
//        if (mURL.endsWith("?ultima"))
//            return true;
//        return false;
//
//    }
//
//    private BrowserField createBrowserField() {
//
//        BrowserField browserField = new BrowserField();
//
//        boolean a = CoverageInfo.isCoverageSufficient(CoverageInfo.COVERAGE_DIRECT);
//        if (a == false) {
//            UiApplication.getUiApplication().invokeLater(new Runnable() {
//
//                public void run() {
//                    Dialog.alert("No Internet Connectivity");
//                    System.exit(0);
//                }
//            });
//
//        } else {
//            PleaseWaitPopupScreen.showScreenAndWait(new Runnable() {
//
//                public void run() {
//                }
//            });
//        }
//
//        browserField.getConfig();
//        browserField.requestContent(targetURL);
//        ProtocolController controller = new ProtocolController(browserField) {
//            public void handleNavigationRequest(BrowserFieldRequest request) throws Exception {
//
//
//                PleaseWaitPopupScreen.showScreenAndWait(new Runnable() {
//
//                    public void run() {
//
//
//                    }
//                });
//
//                String x = request.getURL();
//                if (checkURL(x)) {
//                    BrowserSession b = Browser.getDefaultSession();
//                    b.displayPage(request.getURL());
//                }
//
//
//                InputConnection inputConnection = handleResourceRequest(request);
//                browserField.displayContent(inputConnection, request.getURL());
//
//            }
//        };
//
//        browserField.getConfig().setProperty(BrowserFieldConfig.CONTROLLER, controller);
//
//        browserField.addListener(new BrowserFieldListener() {
//
//
//            public void downloadProgress(BrowserField browserField, ContentReadEvent event) throws Exception {
//
//                super.downloadProgress(browserField, event);
//
//                Application.getApplication().invokeLater(new Runnable() {
//                    public void run() {
//                    }
//                });
//            }
//
//            public void documentLoaded(BrowserField browserField, Document document) throws Exception {
//                super.documentLoaded(browserField, document);
//                pageLoaded = true;
//            }
//        });
//
//        return browserField;
//    }
//
//
//}
//
//public class MobileAppTest {
//}
