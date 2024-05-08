package at.technikum.firstui.viewmodel;

import at.technikum.firstui.event.Event;
import at.technikum.firstui.event.Publisher;
import at.technikum.firstui.service.SearchTermHistoryService;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchHistoryViewModel {

    private final Publisher publisher;
    private final SearchTermHistoryService searchTermHistoryService;

    private final ObservableList<String> searchHistory
            = FXCollections.observableArrayList();
    private final IntegerProperty selectedSearchIndex
            = new SimpleIntegerProperty();

    public SearchHistoryViewModel(
            Publisher publisher,
            SearchTermHistoryService searchTermHistoryService
    ) {
        this.publisher = publisher;
        this.searchTermHistoryService = searchTermHistoryService;

        // if item is selected, fill in search text
        this.selectedSearchIndex.addListener(
                observable -> selectSearchHistory()
        );

        // on search event, update terms in history
        publisher.subscribe(
                Event.SEARCH_TERM_SEARCHED, this::updateSearchHistory
        );
    }

    public void selectSearchHistory() {
        if (selectedSearchIndex.get() == -1) {
            return;
        }

        // TODO send history select event
        publisher.publish(Event.SEARCH_TERM_SELECTED, getSearchHistory().get(selectedSearchIndex.get()));
    }

    private void updateSearchHistory(String message) {
        searchHistory.setAll(searchTermHistoryService.findAll());
    }

    public ObservableList<String> getSearchHistory() {
        return searchHistory;
    }

    public IntegerProperty selectedSearchIndexProperty() {
        return selectedSearchIndex;
    }
}
