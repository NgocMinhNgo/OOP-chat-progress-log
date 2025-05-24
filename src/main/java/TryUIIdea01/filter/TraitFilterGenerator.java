//package filter;
//
//import java.util.Set;
//import java.util.ArrayList;
//
//public class TraitFilterGenerator {
//    private final ArrayList<FilterCreator> creators = new ArrayList<>();
//    private final TraitMapper traitMapper;
//
//    public TraitFilterGenerator(TraitMapper traitMapper) {
//        this.traitMapper = traitMapper;
//        registerDefaultCreators();
//    }
//
//    private void registerDefaultCreators() {
//        registerCreator(new PriceFilterCreator());
//        //registerCreator(new ScreenSizeFilterCreator());
//        //registerCreator(new RAMFilterCreator());
//    }
//
//    public void registerCreator(FilterCreator creator) {
//        creators.add(creator);
//    }
//
//    public FilterCriteria generateFilters(AnalysisResult analysisResult, Class<?> productType) {
//        ArrayList<Filter<?>> filters = new ArrayList<>();
//
//        for (FilterCondition condition : analysisResult.getConditions()) {
//
//            Set<Class<?>> traits = traitMapper.getTraitsForAttribute(condition.getAttribute());
//
//            for (Class<?> trait : traits) {
//                creators.stream()
//                        .filter(c -> c.canCreate(condition.getAttribute(), trait))
//                        .findFirst()
//                        .ifPresent(c -> filters.add(c.create(condition)));
//            }
//        }
//
//        return new FilterCriteria(filters);
//    }
//}

package TryUIIdea01.filter;

import TryUIIdea01.analysis.AnalysisResult;

import java.util.ArrayList;
import java.util.Set;

public class TraitFilterGenerator {
    private final ArrayList<FilterCreator> creators = new ArrayList<>();
    private final TraitMapper traitMapper;

    public TraitFilterGenerator(TraitMapper traitMapper) {
        this.traitMapper = traitMapper;
        registerDefaultCreators();
    }

    private void registerDefaultCreators() {
        registerCreator(new PriceFilterCreator());
        //registerCreator(new ScreenSizeFilterCreator());
        //registerCreator(new RAMFilterCreator());
    }

    public void registerCreator(FilterCreator creator) {
        creators.add(creator);
    }

    public FilterCriteria generateFilters(AnalysisResult analysisResult, Class<?> productType) {
        ArrayList<Filter<?>> filters = new ArrayList<>();

        // Duyệt qua từng điều kiện trong analysisResult
        for (FilterCondition condition : analysisResult.getConditions()) {
            System.out.println("Condition in AnalysisResult: " + condition);

            // Lấy danh sách các trait tương ứng với attribute
            Set<Class<?>> traits = traitMapper.getTraitsForAttribute(condition.getAttribute());

            for (Class<?> trait : traits) {
                System.out.println("trait in traits: " + trait.getSimpleName());
            }

            // Duyệt qua từng trait
            for (Class<?> trait : traits) {
                // Tìm FilterCreator phù hợp
                FilterCreator suitableCreator = null;
                for (FilterCreator creator : creators) {
                    System.out.println("2 cai ne: " + condition.getAttribute() + " " + trait.getSimpleName());

                    if (creator.canCreate(condition.getAttribute(), trait)) {
                        suitableCreator = creator;
                        System.out.println("Suitable creator found!" );
                        break; // Dừng khi tìm thấy creator đầu tiên phù hợp
                    }

                    else{
                        System.out.println("canCreate method not run!" );
                    }
                }

                if(suitableCreator == null) {
                    System.out.println("Suitable creator not found!");
                }

                // Nếu tìm thấy creator phù hợp thì tạo filter và thêm vào danh sách
                if (suitableCreator != null) {
                    Filter<?> newFilter = suitableCreator.create(condition);
                    filters.add(newFilter);
                    System.out.println("Filter: " + newFilter.toString());
                }
                else{
                    System.out.println("No suitable creator found for attribute: " + condition.getAttribute());
                }
            }
        }

        return new FilterCriteria(filters);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TraitFilterGenerator {\n");

        sb.append("  Registered FilterCreators:\n");
        for (FilterCreator creator : creators) {
            sb.append("    - ").append(creator.getClass().getSimpleName()).append("\n");
        }

        sb.append("  TraitMapper: ").append(traitMapper.getClass().getSimpleName()).append("\n");

        sb.append("}");
        return sb.toString();
    }
}