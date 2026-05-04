public class FourLetterDictionary extends AbstractDictionary {
    @Override
    public boolean isValidKey(String key) {
        return key != null && key.length() == 4 && key.matches("[a-zA-Z]{4}");
    }
}