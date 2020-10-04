# Mybatis Criteria Query Helper
Imagine uma consulta com diversos parâmetros opcionais, todos escritos  em um arquivo xml como ```EmployeeMapper.xml```:  
  
```xml  
<select id="findAllWithParameters" resultMap="employeeMap">  
    select   
        e.employee_id  
        e.name,  
        e.birth_date,  
        d.department_id,  
        d.name as department_name,  
        s.section_id,  
        s.name as section_name
    from  
        employee e  
    join section s on s.section_id = e.section_id  
    join department d on d.department_id = s.department_id      
    <where>  
        <if test="e.section.name != null">  
            s.name = #{e.section.name}  
        </if>  
        <if test="e.section.department.name != null">  
            d.name = #{e.section.department.name}  
        </if>  
        <if test="e.name != null">  
            s.name like concat('%', #{e.name}, '%')  
        </if>  
        ...  
        <if test="...">  
            ...  
        </if>  
    </where>  
</select>  
```
À medida que mais informações são requisitadas a esta consulta e mais parâmetros são adicionados, o arquivo cresce, tornando sua leitura cada vez mais difícil e confusa.

Utilizando o ****Criteria Query Helper**** é possível minimizar a complexidade dos testes dentro do XML e tamanho final do arquivo ficando da seguinte forma:
```xml
<select id="findAllWithParameters" resultMap="employeeMap">  
    select   
        e.employee_id  
        e.name,  
        e.birth_date,  
        d.department_id,  
        d.name as department_name,  
        s.section_id,  
        s.name as section_name
    from  
        employee e  
    join section s on s.section_id = e.section_id  
    join department d on d.department_id = s.department_id      
    <where>  
        #{conditions}
    </where>  
</select>  
```

### O que é?
O Criteria Builder Helper é um utilitário inspirado na JPA Criteria API, abstraindo alguns dos principais métodos para construção de parâmetros condicionais de maneira fluida e natural, minimizando a quantidade de código e condições dentro dos arquivos mapper do MyBatis.

### O que não é?
- Uma implementação da JPA Criteria API;
- Um gerador automático de consultas;
- Uma implementação do padrão Repository do Spring Boot;

## Como Utilizar

```xml
<dependency>  
	<groupId>com.github.psinalberth</groupId>  
	<artifactId>mybatis-criteria-query-helper</artifactId>  
	<version>1.0</version>  
</dependency>
```
Após importar a dependência, basta utilizar a classe ```CriteriaBuilder```, incluindo as condições de pesquisa desejadas. Para gerar o SQL final, utilize o método ```generateSql()```, que recebe uma lista de predicates e as renderiza em uma única String, a qual pode ser passada por parâmetro dentro do mapper correspondente;

```java
// EmployeeService.java

public List<Employee> findAllWithParameters(EmployeeFilter filter) {
	List<Predicate> predicates = new ArrayList<>();  
	  
	if (filter.getAge() != null) 	  
		predicates.add(builder.equal("age", 18));   
	}
	
	if (filter.getName() != null) {
		predicates.add(builder.like("name", "%Smith%"));
	}

	String conditions = builder.generateSql(predicates);
	return employeeMapper.findAllWithParameters(conditions);
}

// EmployeeMapper.java
List<Employee> findAllWithParameters(@Param("conditions") String conditions);

```


## Predicates [Condições] implementados
- [x] Equal
	```java
	builder.equal("e.section_id", 14L); 
	```
- [x] Not Equal
	```java
	builder.notEqual("e.employee_id", 1); 
	```
- [x] Greater Than
	```java
	builder.gt("e.salary", BigDecimal.valueOf("3999.42")); 
	builder.greaterThan("e.salary", BigDecimal.valueOf("3999.42")); 
	```
- [x] Greater Than or Equal To
	```java
	builder.ge("e.salary", BigDecimal.valueOf("3999.42")); 
	builder.greaterThanOrEqualTo("e.salary", BigDecimal.valueOf("3999.42")); 
	```
- [x] Less Than
	```java
	builder.lt("e.salary", BigDecimal.valueOf("5000.66")); 
	builder.lessThan("e.salary", BigDecimal.valueOf("5000.66")); 
	```
- [x] Less Than or Equal To
	```java
	builder.le("e.salary", BigDecimal.valueOf("5000.66")); 
	builder.lessThanOrEqualTo("e.salary", BigDecimal.valueOf("5000.66")); 
	```
- [x] Between
	```java
	builder.between("e.birth_date", LocalDate.of(1990, 01, 01), LocalDate.of(1991, 12, 31)); 
	```
- [x] Like
	```java
	builder.like("e.name", "%Smith%"); 
	```
- [x] Is Null
	```java
	builder.isNull("e.email"); 
	```
- [x] In
	```java
	builder.in("e.section_id", 1, 2, 3, 5, 7); 
	builder.in("e.section_id", Arrays.asList(1, 2, 3, 5, 7)); 
	```
- [x] Not
	```java
	builder.not(builder.in("e.section_id", 1, 2, 3, 5, 7)); 
	```
- [ ] Subquery


Quer criar o seu próprio *predicate*? É simples, basta criar uma classe e implementar a interface ```Predicate.java```, juntamente com o método ```render```, responsável pela renderização da sintaxe da condição desejada, como no exemplo abaixo:

```java
public class RegexMatchPredicate implements Predicate {

	...
	private final String expression;
	private final Object value;

	// Getters
	...

	@Override
	public String render(boolean isNegated) {
		return (isNegated ? "not" : "") + getExpression() + " ~ " + getValue();
	}
}
```

## Limitações
Por não se tratar de uma biblioteca ORM, se faz necessário conhecer as estruturas das tabelas e consulta principal, aplicando-se o *alias* adequado para cada relacionamento ao declarar *joins*, por exemplo.
