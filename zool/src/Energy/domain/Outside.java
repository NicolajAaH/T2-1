package Energy.domain;

public class Outside extends Room {

    // Constructor
    public Outside(String description) {
        super(description);
    }

    // Methods
    @Override
    public String getInvDescriptionString() {
        return "Dit hus har:\n";
    }
}
