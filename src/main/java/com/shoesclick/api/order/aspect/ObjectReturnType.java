package com.shoesclick.api.order.aspect;

import com.shoesclick.api.order.exception.ElementNotFoundException;
import com.shoesclick.api.order.exception.ListNotFoundException;
import com.shoesclick.api.order.exception.ResourceException;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Optional;

public enum ObjectReturnType {
	
	 OPTIONAL {
                                 
		@Override
		public boolean isElementNotFound(Object objReturn) {                   
			return !Optional.class.cast(objReturn).isPresent();
		}

		@Override
		public ResourceException getException() {
		    return new ElementNotFoundException("Elemento não encontrado");
		}
	 },
	 OBJECT {

		@Override
		public boolean isElementNotFound(Object objReturn) {
			return objReturn == null;
		}

		 @Override
		 public ResourceException getException() {
			 return new ElementNotFoundException("Elemento não encontrado");
		 }

	 }, SPRING_PAGE {

		public boolean isElementNotFound(Object objReturn) {
			return Page.class.cast(objReturn).isEmpty();
		}

		@Override
		public ResourceException getException() {
			return new ListNotFoundException("Dados não retornado para Consulta");
		}
	}, COLLECTION {

		public boolean isElementNotFound(Object objReturn) {
			return Collection.class.cast(objReturn).isEmpty();
		}

		@Override
		public ResourceException getException() {
			return new ListNotFoundException("Dados não retornado para Consulta");
		}
	}, ENUMMERATION {

		public boolean isElementNotFound(Object objReturn) {
			return Enumeration.class.cast(objReturn).hasMoreElements();
		}

		@Override
		public ResourceException getException() {
			return new ListNotFoundException("Dados não retornado para Consulta");
		}
	};

	public abstract boolean isElementNotFound(Object objReturn);


	public abstract ResourceException getException();

}
