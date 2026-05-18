package fr.iutgon.tp6.modele;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.text.NumberFormat;

public class MoneyTableCell extends TableCell<Ligne, Number> {

    private static final PseudoClass NEGATIF =
            PseudoClass.getPseudoClass("negatif");

    private final NumberFormat format = NumberFormat.getNumberInstance();

    public MoneyTableCell() {
        setAlignment(Pos.CENTER_RIGHT);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
    }

    @Override
    protected void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            pseudoClassStateChanged(NEGATIF, false);
        } else {
            setText(format.format(item));
            pseudoClassStateChanged(NEGATIF, item.doubleValue() < 0);
        }
    }

    public static Callback<TableColumn<Ligne, Number>, TableCell<Ligne, Number>>
    forTableColumn() {
        return col -> new MoneyTableCell();
    }
}