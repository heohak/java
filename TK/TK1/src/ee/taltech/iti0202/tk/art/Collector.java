package ee.taltech.iti0202.tk.art;


import java.util.ArrayList;
import java.util.List;

public class Collector {

    List<Painting> paintings = new ArrayList<>();

    boolean addPainting(Painting painting) {
        if (paintings.contains(painting)) {
            return false;
        }
        for (Painting painting1 : paintings) {
            if (painting1.getTitle().equals(painting.getTitle())) {
                return false;
            }
        }
        paintings.add(painting);
        return true;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (paintings.contains(painting) && !fellowCollector.paintings.contains(painting)) {
            if (fellowCollector.addPainting(painting)) {
                return true;
            }
        }
        return false;
    }



}
