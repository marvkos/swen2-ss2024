package at.technikum.firstui.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.FocusModel;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;

public class InputButtonViewModel {

    private final StringProperty searchText
            = new SimpleStringProperty("");
    private final BooleanProperty disableSearch
            = new SimpleBooleanProperty(true);
    private final ObservableList<String> searchHistory
            = FXCollections.observableArrayList();
    private final IntegerProperty selectedSearchIndex
            = new SimpleIntegerProperty();

    public InputButtonViewModel() {
        // if search text is empty, disable search
        this.searchText.addListener(
                observable -> disableSearch.set(searchText.get().isEmpty())
        );
        // if item is selected, fill in search text
        this.selectedSearchIndex.addListener(
                observable -> selectSearchHistory()
        );
    }

    public void search() {
        if (disableSearch.get()) {
            return;
        }

        searchHistory.add(searchText.get());
        searchText.set("");
    }

    public void selectSearchHistory() {
        if (selectedSearchIndex.get() == -1) {
            return;
        }

        searchText.set(searchHistory.get(selectedSearchIndex.get()));
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

    public ObservableList<String> getSearchHistory() {
        return searchHistory;
    }

    public IntegerProperty selectedSearchIndexProperty() {
        return selectedSearchIndex;
    }
}
