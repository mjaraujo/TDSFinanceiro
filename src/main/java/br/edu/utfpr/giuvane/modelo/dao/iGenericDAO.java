/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.giuvane.modelo.dao;

import java.util.List;

/**
 *
 * @author Giuvane
 */
interface iGenericDAO<T> {
    public void save(T object);
    public T listOne(String pkName, int pkValue, Class clazz);
    public List listAll(Class object);
    public List listAll(final int first,final int max);
    public void update(T object);
    public void delete(T object);
}