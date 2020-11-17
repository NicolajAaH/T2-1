package Energy;

public class Store extends Room {

    public Store(String description) {
        super(description);
    }

    @Override
    public String getInvDescriptionString() {
        return "Du kan købe:\n";
    }
}
