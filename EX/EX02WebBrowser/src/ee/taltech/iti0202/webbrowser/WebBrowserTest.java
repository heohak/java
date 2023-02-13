package ee.taltech.iti0202.webbrowser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebBrowserTest {
    @Test
    public void defaultCurrent() {
        WebBrowser defaultCurrent = new WebBrowser();
        defaultCurrent.homePage();
        assertEquals("google.com", defaultCurrent.getCurrentUrl());
    }

    @Test
    public void onlyGooglePage() {
        WebBrowser onlyGooglePage = new WebBrowser();
        assertEquals("google.com", onlyGooglePage.getCurrentUrl());
        assertEquals(1, onlyGooglePage.getHistory().size());
        onlyGooglePage.forward();
        assertEquals("google.com", onlyGooglePage.getCurrentUrl());
        onlyGooglePage.back();
        assertEquals("google.com", onlyGooglePage.getCurrentUrl());
    }

    @Test
    public void twoPageHistory() {
        WebBrowser twoPage = new WebBrowser();
        twoPage.goTo("neti.ee");
        assertEquals(2, twoPage.getHistory().size());
        assertEquals("neti.ee", twoPage.getCurrentUrl());
        assertEquals("google.com", twoPage.getHistory().get(0));
        assertEquals("neti.ee", twoPage.getHistory().get(1));
    }

    @Test
    public void example1Moodle() {
        WebBrowser example1 = new WebBrowser();
        assertEquals("google.com", example1.getCurrentUrl());
        example1.setHomePage("neti.ee");
        example1.goTo("facebook.com");
        example1.back();
        example1.back();
        assertEquals(3, example1.getHistory().size());
        assertEquals("google.com", example1.getHistory().get(0));
        assertEquals("facebook.com", example1.getHistory().get(1));
        assertEquals("google.com", example1.getHistory().get(2));
    }

    @Test
    public void example2Moodle() {
        WebBrowser example2 = new WebBrowser();
        example2.setHomePage("neti.ee");
        example2.goTo("facebook.com");
        example2.forward();
        example2.forward();
        assertEquals(2, example2.getHistory().size());
        assertEquals("google.com", example2.getHistory().get(0));
        assertEquals("facebook.com", example2.getHistory().get(1));

    }

    @Test
    public void example3Moodle() {
        WebBrowser example3 = new WebBrowser();
        example3.setHomePage("neti.ee");
        example3.goTo("facebook.com");
        example3.back();
        example3.homePage();
        example3.forward();
        assertEquals(4, example3.getHistory().size());
        assertEquals("google.com", example3.getHistory().get(0));
        assertEquals("facebook.com", example3.getHistory().get(1));
        assertEquals("google.com", example3.getHistory().get(2));
        assertEquals("neti.ee", example3.getHistory().get(3));

    }

    @Test
    public void example4Moodle() {
        final int SIX = 6;
        WebBrowser example4 = new WebBrowser();
        assertEquals("google.com", example4.getCurrentUrl());
        example4.setHomePage("neti.ee");
        example4.goTo("facebook.com");
        assertEquals("facebook.com", example4.getCurrentUrl());
        example4.goTo("google.com");
        assertEquals("google.com", example4.getCurrentUrl());
        example4.back();
        assertEquals("facebook.com", example4.getCurrentUrl());
        example4.addAsBookmark();
        example4.forward();
        assertEquals("google.com", example4.getCurrentUrl());
        example4.homePage();
        assertEquals("neti.ee", example4.getCurrentUrl());
        example4.addAsBookmark();
        assertEquals(2, example4.getBookmarks().size());
        assertEquals(SIX, example4.getHistory().size());
        assertEquals("google.com", example4.getHistory().get(0));
        assertEquals("facebook.com", example4.getHistory().get(1));
        assertEquals("google.com", example4.getHistory().get(2));
        assertEquals("facebook.com", example4.getHistory().get(3));
        assertEquals("google.com", example4.getHistory().get(4));
        assertEquals("neti.ee", example4.getHistory().get(5));
    }

    @Test
    public void example5Moodle() {
        WebBrowser example4 = new WebBrowser();
        example4.goTo("google.com");
        example4.goTo("google.com");
        assertEquals(1, example4.getHistory().size());
        assertEquals("google.com - 1 visit", example4.getTop3VisitedPages());
    }
    @Test
    public void example6Moodle() {
        WebBrowser example6 = new WebBrowser();
        example6.goTo("neti.ee");
        example6.goTo("facebook.com");
        example6.back();
        example6.forward();
        assertEquals(5, example6.getHistory().size());
        assertEquals("google.com", example6.getHistory().get(0));
        assertEquals("neti.ee", example6.getHistory().get(1));
        assertEquals("facebook.com", example6.getHistory().get(2));
        assertEquals("neti.ee", example6.getHistory().get(3));
        assertEquals("facebook.com", example6.getHistory().get(4));
        assertEquals("neti.ee - 2 visits\nfacebook.com - 2 visits\ngoogle.com - 1 visit",
                example6.getTop3VisitedPages());
    }
    @Test
    public void addSameBookmarkTwice() {
        WebBrowser twoBookmarks = new WebBrowser();
        twoBookmarks.goTo("yt.com");
        twoBookmarks.addAsBookmark();
        twoBookmarks.addAsBookmark();
        assertEquals(1, twoBookmarks.getBookmarks().size());


    }




}
