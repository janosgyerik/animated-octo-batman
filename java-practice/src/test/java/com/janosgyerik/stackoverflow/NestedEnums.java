package com.janosgyerik.stackoverflow;

enum InnerEnum {
    A, B
}

enum OuterEnum {
    X, Y
}

enum CombinedEnum {
    A_X(InnerEnum.A, OuterEnum.X),
    A_Y(InnerEnum.A, OuterEnum.Y),
    B_X(InnerEnum.B, OuterEnum.X),
    B_Y(InnerEnum.B, OuterEnum.Y)
    ;

    private final InnerEnum inner;
    private final OuterEnum outer;

    CombinedEnum(InnerEnum inner, OuterEnum outer) {
        this.inner = inner;
        this.outer = outer;
    }
}

public class NestedEnums {
}
