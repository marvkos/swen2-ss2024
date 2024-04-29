package at.technikum.firstui.view;

import at.technikum.firstui.viewmodel.InputButtonViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InputButtonView implements Initializable {

    private final InputButtonViewModel viewModel;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<String> searchHistoryList;

    public InputButtonView() {
        this.viewModel = new InputButtonViewModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.searchField.textProperty()
                .bindBidirectional(viewModel.searchTextProperty());
        this.searchButton.disableProperty()
                .bind(viewModel.disableSearchProperty());
        this.searchHistoryList
                .setItems(viewModel.getSearchHistory());
        this.viewModel.selectedSearchIndexProperty()
                .bind(searchHistoryList.getSelectionModel().selectedIndexProperty());
    }

    @FXML
    public void onSearch() {
        this.viewModel.search();
    }
}
