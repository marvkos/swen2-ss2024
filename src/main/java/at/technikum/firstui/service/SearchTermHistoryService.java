package at.technikum.firstui.service;

import at.technikum.firstui.entity.SearchTerm;
import at.technikum.firstui.entity.SelectedTime;
import at.technikum.firstui.repository.SearchTermRepository;

import java.util.List;
import java.util.Optional;

public class SearchTermHistoryService {

    private final SearchTermRepository searchTermRepository;

    public SearchTermHistoryService(SearchTermRepository searchTermRepository) {
        this.searchTermRepository = searchTermRepository;
    }

    public void add(String term) {
        Optional<SearchTerm> searchTerm = searchTermRepository.findByTerm(term);

        if (searchTerm.isPresent()) {
            return;
        }

        searchTermRepository.save(new SearchTerm(term));
    }

    public void addSelectedTimeToTerm(String term) {
        Optional<SearchTerm> searchTermOptional = searchTermRepository.findByTerm(term);

        if (searchTermOptional.isEmpty()) {
            return;
        }

        SearchTerm searchTerm = searchTermOptional.get();

        SelectedTime selectedTime = new SelectedTime();
        searchTerm.addSelectedTime(selectedTime);

        searchTermRepository.update(searchTerm);
    }

    public List<String> findAll() {
        return searchTermRepository.findAll().stream()
                .map(SearchTerm::getTerm)
                .toList();
    }
}
