<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:admin_template title="Contatos" breadcrumb="Dashboard">

	<jsp:attribute name="content">
		
     	<div class="row">
          <div class="col-lg-12">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Novo Contato</h1>
              </div>
              <form class="user" action="${pageContext.request.contextPath}/contact-controller-servlet" method="post">
                <div class="form-group">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="text" class="form-control form-control-user" id="name" 
                    	placeholder="Informe o nome" required="required" name="name">
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                  	<input type="email" class="form-control form-control-user" id="email" 
                  		placeholder="Informe o Email" required="required" name="email">
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="text" class="form-control form-control-user" id="phone" 
                    	placeholder="Informe o telefone" required="required" name="phone">
                  </div>
                </div>
                <input type="hidden" name="action" value="createNewContact" />
                <div align="center">
                    <button type="submit" class="btn btn-primary">Salvar</button>
                </div>
              </form>
                
            </div>
          </div>
        </div>
					
	</jsp:attribute>

</mt:admin_template>
