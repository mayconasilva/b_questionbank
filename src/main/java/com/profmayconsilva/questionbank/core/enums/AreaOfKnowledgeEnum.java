package com.profmayconsilva.questionbank.core.enums;

public enum AreaOfKnowledgeEnum {
    CIENCIAS("Ciências da Natureza", "CI"),
    CIENCIAS_HUMANAS("Ciências Humanas", "CH"),
    CIENCIAS_HUMA_TEC("Ciências Humanas e Suas Tecnologias", "CHST"),
    CIENCIAS_NATUREZA("Ciências da Natureza e Suas Tecnologias", "CNST"),
    ENSINO_RELIGIOSO("Ensino Religioso", "ER"),
    LINGUAGENS("Linguagens", "LING"),
    LINGUAGENS_CODIGOS("Linguagens, Códigos e Suas Tecnologias", "LCST"),
    MATEMATICA("Matemática", "MAT"),
    MATEMATICA_TECNOLOGIAS("Matemática e Suas Tecnologias", "MST");

    private String areaName;
    private String acronymName;

    AreaOfKnowledgeEnum(String areaName, String acronymName) {
        this.areaName = areaName;
        this.acronymName = acronymName;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getAcronymName() {
        return acronymName;
    }
}
