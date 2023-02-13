package ee.taltech.iti0202.webbrowser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class WebBrowser {
    private String homePage;
    private List<String> history;
    private List<String> bookmarks;
    private String currentUrl;
    private int currentIndex;
    public WebBrowser() {
        this.homePage = "google.com";
        this.history = new ArrayList<>();
        this.bookmarks = new ArrayList<>();
        this.currentUrl = homePage;
        this.currentIndex = 0;
        history.add("google.com");
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        goTo(homePage);
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (currentIndex > 0) {
            if (currentUrl != getHistory().get(currentIndex - 1)) {
                history.add(getHistory().get(currentIndex - 1));
                currentIndex--;
            }
        }
        currentUrl = getHistory().get(currentIndex);
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (currentIndex < getHistory().size() - 1) {
            if (currentUrl != getHistory().get(currentIndex + 1)) {
                history.add(getHistory().get(currentIndex + 1));
            }
            currentIndex++;

        }
        currentUrl = getHistory().get(currentIndex);
    }

    /**
     * Go to a webpage.
     *
     * @param url where to go
     */
    public void goTo(String url) {
        if (!Objects.equals(url, getCurrentUrl())) {
            history.add(url);
            currentIndex = getHistory().size() - 1;
        }
        currentUrl = url;
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
        } map1 = map1.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        StringBuilder result = new StringBuilder();
        String v1;
        int count = 0;
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (count >= 3) {
                break;
            }
            if (entry.getValue() == 1) {
                v1 = "visit";
            } else {
                v1 = "visits";
            }
            if ((count == 0 || count == 1) && map1.size() >= 3) {
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
