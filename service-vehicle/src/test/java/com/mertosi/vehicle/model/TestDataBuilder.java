package com.mertosi.vehicle.model;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.jeasy.random.randomizers.text.StringRandomizer;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static org.jeasy.random.FieldPredicates.isAnnotatedWith;
import static org.jeasy.random.FieldPredicates.named;

public abstract class TestDataBuilder<T> {

    private static final PositiveIntegerRandomizer positiveIntegerRandomizer = new PositiveIntegerRandomizer();

    private static final CharacterRandomizer characterRandomizer = new CharacterRandomizer();

    protected final EasyRandom generator;
    protected T data;
    Class<T> clazz;

    public TestDataBuilder(Class<T> clazz) {
        this(clazz, false);
    }

    public TestDataBuilder(Class<T> clazz, boolean excludeRelations) {
        generator = new EasyRandom(getExclusionParameters(excludeRelations));

        this.clazz = clazz;
        data = generator.nextObject(clazz);
    }

    private EasyRandomParameters getExclusionParameters(boolean excludeRelations) {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.randomize(Integer.class, positiveIntegerRandomizer);
        parameters.randomize(String.class, characterRandomizer);

        if (!excludeRelations) return parameters;

        parameters.excludeField(isAnnotatedWith(ManyToOne.class, OneToMany.class, OneToOne.class, ManyToOne.class).or(named("id")));
        return parameters;
    }

    public T build() {
        return data;
    }
}

class PositiveIntegerRandomizer extends IntegerRangeRandomizer {
    public PositiveIntegerRandomizer() {
        super(0, 100);
    }

    @Override
    protected Integer getDefaultMinValue() {
        return 0;
    }
}

class CharacterRandomizer extends StringRandomizer {

    @Override
    public String getRandomValue() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}

