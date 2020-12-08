package Energy.domain;

class Outside extends Room {

    // CONSTRUCTOR
    public Outside(String description, String name) {
        super(description, name);
    }

    // METODER
    @Override
    public String getInvDescriptionString() {
        return "Dit hus har:\n";
    }
}
