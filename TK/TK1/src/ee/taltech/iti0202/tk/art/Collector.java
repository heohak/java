package ee.taltech.iti0202.tk.art;


import java.util.ArrayList;
import java.util.List;

public class Collector {

    private List<Painting> paintings = new ArrayList<>();

    /**
     *
     * @param painting
     * @return boolean
     */
    public boolean addPainting(Painting painting) {
        if (!paintings.contains(painting)) {
            for (Painting p : paintings) {
                if (p.getTitle().equals(painting.getTitle())) {
                    return false;
                }
            }
            paintings.add(painting);
            return true;
        }
        return false;
    }

    /**
     *
     * @param painting
     * @param fellowCollector
     * @return boolean
     */
    public boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (paintings.contains(painting) && fellowCollector != this && fellowCollector.addPainting(painting)) {
            paintings.remove(painting);
            return true;
        }
        return false;
    }

    /**
     *
     * @return List
     */
    public List<Painting> getPaintings() {
        return paintings;
    }
}
