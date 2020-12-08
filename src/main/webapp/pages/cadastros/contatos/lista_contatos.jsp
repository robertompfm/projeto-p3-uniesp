<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:admin_template title="Contatos" breadcrumb="Dashboard">
	
	<jsp:attribute name="content">
		
		<section class="content">
	
	     <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Lista de Contatos</h6>
		     <div style="margin-left: 180px; margin-top: -30px;">
		     	<a class="btn btn-primary" href="${pageContext.request.contextPath}/pages/cadastros/contatos/cadastro_contatos.jsp">Novo</a>
		     </div>
            </div>
            
			  <c:if test="${not empty success}">
	            <div class="alert alert-success" role="alert">${success}</div>
			  </c:if>
			  
			   <c:if test="${not empty fail}">
	            <div class="alert alert-danger" role="alert">${fail}</div>
			  </c:if>
            
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Nome</th>
                      <th>E-mail</th>
                      <th>Telefone</th>
                      <th>Status</th>
                      <th>Ativar/Desativar</th>
                      <th>Editar</th>
                      <th>Visualizar</th>
                      <th>Excluir</th>
                    </tr>
                  </thead>
                 
                  <tbody>
                    <c:forEach var="c" items="${contacts}">
			            <input type="hidden" value="${c.id}">
                    	<tr>			                  
	                    	<td>
	                    	  <c:out value="${c.name}"/>	
	                    	</td>
	                    	<td>
	                    	  <c:out value="${c.email}"/>	
	                    	</td>
	                    	<td>
	                    	  <c:out value="${c.phone}"/>	
	                    	</td>
	                    	<td>
	                    	  <c:out value="${c.active ? 'Ativo' : 'Inativo'}"/>	
	                    	</td>
	                    	
	                    	<td>
	                    		<form action="${pageContext.request.contextPath}/contact-controller-servlet" method="post">
	                    			<input type="hidden" name="id" value="${c.id}" />
	                    			<input type="hidden" name="name" value="${c.name}" />
	                    			<input type="hidden" name="action" value="toggleContactStatus" />
            						<button type="submit" class="${c.active ? "btn btn-danger" : "btn btn-success"}">
            							${c.active ? "Desativar" : "Ativar"}
            						</button>
	                    		</form>
	                    	</td>
	                    	<td>
	                    		<form action="${pageContext.request.contextPath}/contact-controller-servlet" method="post">
	                    			<input type="hidden" name="id" value="${c.id}" />
	                    			<input type="hidden" name="name" value="${c.name}" />
	                    			<input type="hidden" name="action" value="redirectToUpdateContact" />
            						<button type="submit" class="btn btn-warning"}>
            							Editar
            						</button>
	                    		</form>
	                    	</td>
	                    	<td>
	                    		<form action="${pageContext.request.contextPath}/contact-controller-servlet" method="post">
	                    			<input type="hidden" name="id" value="${c.id}" />
	                    			<input type="hidden" name="name" value="${c.name}" />
	                    			<input type="hidden" name="action" value="seeContactInfo" />
            						<button type="submit" class="btn btn-primary"}>
            							Visualizar
            						</button>
	                    		</form>
	                    	</td>
	                    	<td>
	                    		<form action="${pageContext.request.contextPath}/contact-controller-servlet" method="post">
	                    			<input type="hidden" name="id" value="${c.id}" />
	                    			<input type="hidden" name="name" value="${c.name}" />
	                    			<input type="hidden" name="action" value="removeContact" />
            						<button type="submit" class="btn btn-secondary"}>
            							<i class="fa fa-trash" aria-hidden="true"></i>
            						</button>
	                    		</form>
	                    	</td>
                    	</tr>
                    	
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
     	
     </section>
					
	</jsp:attribute>
	
</mt:admin_template>
