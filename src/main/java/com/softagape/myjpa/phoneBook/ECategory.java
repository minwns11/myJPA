//package com.softagape.myjpa.phoneBook;
//
//public enum ECategory {
//    Friends(0),
//    Families(1),
//    Schools(2),
//    Jobs(3),
//    Hobbies(4);
//
//    private final Integer value;
//    ECategory(Integer value) {
//        this.value = value;
//    }
//    public Integer getValue() {
//        return this.value;
//    }
//    private static final ECategory[] ECategoryArray = ECategory.values();
//    public static ECategory integerOf(Integer value) {
//        for ( ECategory item : ECategoryArray ) {
//            if ( item.getValue() == value ) {
//                return item;
//            }
//        }
//        throw new IllegalArgumentException("ECategory value :" + value);
//    }
//}
