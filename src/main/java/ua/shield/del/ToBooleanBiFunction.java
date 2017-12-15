package ua.shield.del;

/**
 * Created by sa on 12.12.17.
 */
@FunctionalInterface
public interface ToBooleanBiFunction <T, U> {
       boolean applyAsBoolean(T t, U u);
   }
