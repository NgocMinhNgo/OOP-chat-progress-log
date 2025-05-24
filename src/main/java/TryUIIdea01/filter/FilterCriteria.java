package TryUIIdea01.filter;

import java.util.ArrayList;
import java.util.Collection;

// Lớp filter.FilterCriteria: quản lý nhiều bộ lọc và áp dụng chúng
//public class filter.FilterCriteria {
//    private final ArrayList<filter.Filter<?>> filters;  // Danh sách các filter
//
//    // Constructor: nhận danh sách filter và khóa nó lại (không cho sửa)
//    public filter.FilterCriteria(ArrayList<filter.Filter<?>> filters) {
//        this.filters = new ArrayList<>(filters); // sao chép để tránh sửa từ bên ngoài
//    }
//
//    // Áp dụng tất cả các filter lên danh sách items
//    public <T> List<T> applyFilters(Collection<T> items) {
//        List<T> result = new ArrayList<>(); // danh sách kết quả sau khi lọc
//
//        for (T item : items) {
//            boolean passAll = true;
//
//            // Kiểm tra xem item có thỏa tất cả filter không
//            for (filter.Filter<?> filter : filters) {
//                if (!matchesFilter(item, filter)) {
//                    passAll = false; // nếu 1 filter không thỏa -> loại
//                    break;
//                }
//            }
//
//            if (passAll) {
//                result.add(item); // nếu thỏa tất cả thì giữ lại
//            }
//        }
//
//        return result;
//    }
//
//    // Kiểm tra 1 filter có áp dụng được cho item không
//    @SuppressWarnings("unchecked")
//    private <T> boolean matchesFilter(T item, filter.Filter<?> filter) {
//        try {
//            filter.Filter<T> typedFilter = (filter.Filter<T>) filter; // ép kiểu đúng
//            return typedFilter.apply(item);
//        } catch (ClassCastException e) {
//            // Nếu filter không áp dụng cho kiểu này thì bỏ qua
//            return true;
//        }
//    }
//
//    // Kết hợp nhiều filter.FilterCriteria lại với nhau
//    public filter.FilterCriteria combine(filter.FilterCriteria other) {
//        ArrayList<filter.Filter<?>> combined = new ArrayList<>();
//        combined.addAll(this.filters);      // thêm filters của hiện tại
//        combined.addAll(other.filters);     // thêm filters của bộ khác
//
//        return new filter.FilterCriteria(combined); // trả về bộ lọc mới
//    }
//}

// Lớp filter.FilterCriteria: quản lý nhiều bộ lọc và áp dụng chúng
public class FilterCriteria {
    private final ArrayList<Filter<?>> filters;  // Danh sách các filter

    // Constructor: nhận danh sách filter và sao chép lại để tránh sửa từ bên ngoài
    public FilterCriteria(ArrayList<Filter<?>> filters) {
        this.filters = new ArrayList<>(filters);
    }

    // Áp dụng tất cả các filter lên danh sách items
    public <T> ArrayList<T> applyFilters(Collection<T> items) {
        ArrayList<T> result = new ArrayList<>(); // danh sách kết quả sau khi lọc

        for (T item : items) {
            boolean passAll = true;

            // Kiểm tra xem item có thỏa tất cả filter không
            for (Filter<?> filter : filters) {
                if (!matchesFilter(item, filter)) {
                    passAll = false; // nếu 1 filter không thỏa -> loại
                    break;
                }
            }

            if (passAll) {
                result.add(item); // nếu thỏa tất cả thì giữ lại
            }
        }

        return result;
    }

    // Kiểm tra 1 filter có áp dụng được cho item không
    @SuppressWarnings("unchecked")
    private <T> boolean matchesFilter(T item, Filter<?> filter) {
        try {
            Filter<T> typedFilter = (Filter<T>) filter; // ép kiểu đúng
            return typedFilter.apply(item);
        } catch (ClassCastException e) {
            // Nếu filter không áp dụng cho kiểu này thì bỏ qua
            return true;
        }
    }

    // Kết hợp nhiều filter.FilterCriteria lại với nhau
    public FilterCriteria combine(FilterCriteria other) {
        ArrayList<Filter<?>> combined = new ArrayList<>();
        combined.addAll(this.filters);      // thêm filters của hiện tại
        combined.addAll(other.filters);     // thêm filters của bộ khác

        return new FilterCriteria(combined); // trả về bộ lọc mới
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FilterCriteria {\n");
        for (Filter<?> filter : filters) {
            sb.append("  ").append(filter).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}