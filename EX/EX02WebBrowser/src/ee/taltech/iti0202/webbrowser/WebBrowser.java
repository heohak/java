package ee.taltech.iti0202.webbrowser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class WebBrowser {
    private String homePage = "google.com";
    private List<String> history = new ArrayList<>();
    private List<String> bookmarks = new ArrayList<>();
    private String currentUrl = "google.com";
    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();
    public WebBrowser() {
        history.add("google.com");
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        if (!homePage.equals(currentUrl)) {
            history.add(homePage);
            backStack.add(currentUrl);
            forwardStack.clear();
            currentUrl = homePage;
        }
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (!backStack.isEmpty()) {
            forwardStack.add(currentUrl);
            currentUrl = backStack.pop();
            history.add(currentUrl);
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (!forwardStack.isEmpty()) {
            backStack.add(currentUrl);
            currentUrl = forwardStack.pop();
            history.add(currentUrl);
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url where to go
     */
    public void goTo(String url) {
        if (!currentUrl.equals(url)) {
            forwardStack.clear();
            backStack.add(currentUrl);
            currentUrl = url;
            history.add(currentUrl);
        }
    }

    /**
     * Add the current webpage as a bookmark.
     */
    public void addAsBookmark() {
        if (!bookmarks.contains(currentUrl)) {
            bookmarks.add(currentUrl);
        }
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookmarks.remove(bookmark);
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }


    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        Map<String, Integer> map1 = new HashMap<>();
        for (String site : getHistory()) {
            map1.put(site, map1.getOrDefault(site, 0) + 1);
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map1.entrySet());
        entries.sort((o1, o2) -> {
            int valueCompare = o2.getValue().compareTo(o1.getValue());
            return valueCompare != 0 ? valueCompare : getHistory().indexOf(o1.getKey()) - getHistory()
                    .indexOf(o2.getKey());
        });

        // Put the sorted entries back into a map
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        StringBuilder result = new StringBuilder();
        String v1;
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            if (count >= 3) {
                break;
            }
            if (entry.getValue() == 1) {
                v1 = "visit";
            } else {
                v1 = "visits";
            }
            if ((count == 0 || count == 1) && map1.size() >= 2) {
                result.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" ").append(v1)
                        .append("\n");
            } else {
                result.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" ").append(v1);
            }
            count++;


        }
        return result.toString();
    }

    /**
     * Returns a list of all visited pages.
     * <p>
     * Not to be confused with pages in your back-history.
     * <p>
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        return history;
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        return currentUrl;
    }
}
