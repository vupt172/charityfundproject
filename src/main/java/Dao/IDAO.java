package Dao;

import java.util.List;

public interface IDAO<T> {
  List<T> search(String name);
  T findById(int id);
  T findByName(String name);
  T save(T item);
  boolean update(T item);
  boolean delete(int id);
}
