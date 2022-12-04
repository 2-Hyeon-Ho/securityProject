package com.nhnacademy.springjpa.repository.familyRelation;

import com.nhnacademy.springjpa.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyRelationRepositoryImpl extends QuerydslRepositorySupport implements FamilyRelationRepositoryCustom {
    public FamilyRelationRepositoryImpl() {
        super(FamilyRelationship.class);
    }
}
