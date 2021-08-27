package by.epam.dkozyrev1.ecafe.dao.builder.limiter;

public enum Limiter {

    EQUALS,
    NOT_EQUALS(true),
    MORE,
    NOT_MORE(true),
    LESS,
    NOT_LESS(true),
    MORE_OR_EQUALS,
    NOT_MORE_OR_EQUALS(true),
    LESS_OR_EQUALS,
    NOT_LESS_OR_EQUALS(true);

    private final boolean isReversed;

    Limiter(boolean isReversed){
        this.isReversed = isReversed;
    }
    Limiter(){
        this.isReversed = false;
    }

    public boolean isReversed(){
        return isReversed;
    }

    @Override
    public String toString() {
        return switch (this){
            case EQUALS, NOT_EQUALS -> "=";
            case LESS, NOT_LESS -> "<";
            case MORE, NOT_MORE -> ">";
            case LESS_OR_EQUALS, NOT_LESS_OR_EQUALS -> "<=";
            case MORE_OR_EQUALS, NOT_MORE_OR_EQUALS -> ">=";
        };
    }

}
