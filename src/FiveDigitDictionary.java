public class FiveDigitDictionary extends AbstractDictionary {
    @Override
    public boolean isValidKey(String key) {
        return key != null && key.length() == 5 && key.matches("\\d{5}");
    }
}