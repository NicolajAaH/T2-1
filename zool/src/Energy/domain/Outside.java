package Energy.domain;

class Outside extends Room {

    // Constructor
    public Outside(String description, String name) {
        super(description, name);
    }

    // Metoder
    @Override
    public String getInvDescriptionString() {
        return "Dit hus har:\n";
    }
}
