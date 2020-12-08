package Energy.domain;

class Store extends Room {

    // CONSTRUCTOR
    public Store(String description, String name) {
        super(description, name);
    }

    // METODER
    @Override
    public String getInvDescriptionString() {
        return "Du kan købe:\n";
    }
}
