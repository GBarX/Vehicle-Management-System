public interface Bookable {
    void bookMe(String startDate, String endDate) throws Exception;
    void cancelMe(String cancelDate) throws Exception;
}
