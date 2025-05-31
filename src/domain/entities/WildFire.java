package domain.entities;
import java.time.LocalDate;

/**
 * Representa um evento de incêndio florestal com identificador, data e nível de severidade.
 * <p>
 * Esta classe armazena informações sobre um incêndio florestal, incluindo seu ID único,
 * a data em que ocorreu e sua severidade.
 * </p>
 *
 */
public class WildFire {
    private int id;
    private LocalDate date;
    private int severity;

    public WildFire(int id, LocalDate date, int severity) {
        this.id = id;
        this.date = date;
        this.severity = severity;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSeverity() {
        return severity;
    }

}
