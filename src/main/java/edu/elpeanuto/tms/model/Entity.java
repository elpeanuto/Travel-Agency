package edu.elpeanuto.tms.model;

import java.io.Serializable;

/**
 * Model marker class
 * @param <T> generic value
 */
public abstract class Entity<T> implements Serializable, Comparable<T> {
}
