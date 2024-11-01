//package com.a3_backend.TAD;
//
//import com.a3_backend.model.Usuario;
//import org.hibernate.type.TypeFactory;
//import org.hibernate.HibernateException;
//import org.hibernate.annotations.CollectionType;
//import org.hibernate.boot.spi.MetadataImplementor;
//import org.hibernate.collection.spi.PersistentCollection;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.metamodel.CollectionClassification;
//import org.hibernate.persister.collection.CollectionPersister;
//import org.hibernate.usertype.UserCollectionType;
//import java.util.Iterator;
//import java.util.Map;
//
//public class ListaEncadeadaType implements UserCollectionType {
//    @Override
//    public Object instantiate(int anticipatedSize) {
//        return new ListaEncadeada<>();
//    }
//
//    @Override
//    public CollectionClassification getClassification() {
//        return null;
//    }
//
//    @Override
//    public Class<?> getCollectionClass() {
//        return null;
//    }
//    public CollectionType getCollectionType(TypeFactory typeFactory, MetadataImplementor metadata, String role, String propertyRef, boolean isEmbeddedInXML) {
////        return typeFactory.list(role, String.valueOf(typeFactory.byClass(Usuario.class.getName())));
//        return null;
//    }
//
//    @Override
//    public PersistentCollection<?> instantiate(SharedSessionContractImplementor sharedSessionContractImplementor, CollectionPersister collectionPersister) throws HibernateException {
//        return null;
//    }
//
//    @Override
//    public PersistentCollection<?> wrap(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
//        return null;
//    }
//
//    @Override
//    public Iterator<?> getElementsIterator(Object o) {
//        return null;
//    }
//
//    @Override
//    public boolean contains(Object o, Object o1) {
//        return false;
//    }
//
//    @Override
//    public Object indexOf(Object o, Object o1) {
//        return null;
//    }
//
//    @Override
//    public Object replaceElements(Object o, Object o1, CollectionPersister collectionPersister, Object o2, Map map, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
//        // Lógica para substituir os elementos da coleção original pelos da coleção target
//        ListaEncadeada<Object> originalList = (ListaEncadeada<Object>) o;
//        ListaEncadeada<Object> targetList = (ListaEncadeada<Object>) o1;
//
//        originalList.clear();
//        originalList.addAll(targetList);
//
//        return originalList;
//    }
//}