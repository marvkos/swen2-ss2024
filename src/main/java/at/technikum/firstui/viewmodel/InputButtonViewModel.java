package at.technikum.firstui.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InputButtonViewModel {

    private final StringProperty searchText
            = new SimpleStringProperty("");
    private final BooleanProperty disableSearch
            = new SimpleBooleanProperty(true);

    public InputButtonViewModel() {
        // if search text is empty, disable search
        this.searchText.addListener(
                observable -> disableSearch.set(searchText.get().isEmpty())
        );
    }

    public String getSearchText() {
        return searchText.get();
    }

    public StringProperty searchTextProperty() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText.set(searchText);
    }

    public boolean getDisableSearch() {
        return disableSearch.get();
    }

    public BooleanProperty disableSearchProperty() {
        return disableSearch;
    }

    public void setDisableSearch(boolean disableSearch) {
        this.disableSearch.set(disableSearch);
    }
}
