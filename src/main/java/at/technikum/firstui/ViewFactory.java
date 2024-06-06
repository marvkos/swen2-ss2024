package at.technikum.firstui;

import at.technikum.firstui.event.Publisher;
import at.technikum.firstui.repository.SearchTermDatabaseRepository;
import at.technikum.firstui.repository.SearchTermRepository;
import at.technikum.firstui.service.SearchTermHistoryService;
import at.technikum.firstui.view.SearchHistoryView;
import at.technikum.firstui.view.SearchView;
import at.technikum.firstui.viewmodel.SearchHistoryViewModel;
import at.technikum.firstui.viewmodel.SearchViewModel;

public class ViewFactory {

    private static ViewFactory instance;

    private final Publisher publisher;

    private final SearchTermRepository searchTermRepository;

    private final SearchTermHistoryService searchTermHistoryService;

    private final SearchViewModel searchViewModel;
    private final SearchHistoryViewModel searchHistoryViewModel;

    private ViewFactory() {
        publisher = new Publisher();

        searchTermRepository = new SearchTermDatabaseRepository();

        searchTermHistoryService = new SearchTermHistoryService(searchTermRepository);

        searchViewModel = new SearchViewModel(publisher, searchTermHistoryService);
        searchHistoryViewModel = new SearchHistoryViewModel(publisher, searchTermHistoryService);
    }

    public static ViewFactory getInstance() {
        if (null == instance) {
            instance = new ViewFactory();
        }

        return instance;
    }

    public Object create(Class<?> viewClass) {
        if (SearchView.class == viewClass) {
            return new SearchView(searchViewModel);
        }

        if (SearchHistoryView.class == viewClass) {
            return new SearchHistoryView(searchHistoryViewModel);
        }

        throw new IllegalArgumentException("Unknown view class: " + viewClass);
    }
}
