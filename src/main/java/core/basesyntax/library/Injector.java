package core.basesyntax.library;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.factory.Factory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Injector {
    public static Object getInstance(Class clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        Object instance = declaredConstructor.newInstance();
        Field[] declaredField = clazz.getDeclaredFields();
        for (Field field : declaredField) {
            if (field.getAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                if (field.getType().equals(BetDao.class)) {
                    if (Factory.getBetDao().getClass().isAnnotationPresent(Dao.class)) {
                        field.set(instance, Factory.getBetDao());
                    } else {
                        throw new RuntimeException();
                    }
                } else {
                    if (field.getType().equals(UserDao.class)) {
                        if (Factory.getUserDao().getClass().isAnnotationPresent(Dao.class)) {
                            field.set(instance, Factory.getUserDao());
                        } else {
                            throw new RuntimeException();
                        }
                    }
                }
            }
        }
        return instance;
    }
}
