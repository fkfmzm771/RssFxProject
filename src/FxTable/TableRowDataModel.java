package FxTable;

import javafx.beans.property.StringProperty;

public class TableRowDataModel {
    private StringProperty title;
    private StringProperty category;
    private StringProperty pubDate;
    private StringProperty seeder;
    private StringProperty down;
    private StringProperty size;
    private StringProperty pageLink;


    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public StringProperty pubDateProperty() {
        return pubDate;
    }

    public StringProperty seederProperty() {
        return seeder;
    }

    public StringProperty downProperty() {
        return down;
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public StringProperty pageLinkProperty() {
        return pageLink;
    }

    public TableRowDataModel(StringProperty title, StringProperty category, StringProperty pubDate, StringProperty seeder, StringProperty down, StringProperty size, StringProperty pageLink) {
        this.title = title;
        this.category = category;
        this.pubDate = pubDate;
        this.seeder = seeder;
        this.down = down;
        this.size = size;
        this.pageLink = pageLink;


    }
}
