package ru.javawebinar.topjava;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

public abstract class AbstractWithJPA extends AbstractUserServiceTest
{
    @Autowired
    protected JpaUtil jpaUtil;

    @Override
    @Before
    public void setUp() throws Exception
    {
        service.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
