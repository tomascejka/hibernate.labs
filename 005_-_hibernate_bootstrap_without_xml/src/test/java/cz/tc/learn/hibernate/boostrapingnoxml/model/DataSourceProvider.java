package cz.tc.learn.hibernate.boostrapingnoxml.model;

import com.vladmihalcea.hpjp.util.ReflectionUtils;
import org.hibernate.dialect.Dialect;

import java.util.Properties;
import javax.sql.DataSource;

/**
 * @author Vlad Mihalcea
 */
public interface DataSourceProvider {

	enum IdentifierStrategy {
		IDENTITY,
		SEQUENCE
	}

	String hibernateDialect();

	DataSource dataSource();

	Class<? extends DataSource> dataSourceClassName();

	Properties dataSourceProperties();

	String url();

	String username();

	String password();

	Database database();

	Queries queries();

	default Class<? extends Dialect> hibernateDialectClass() {
		return ReflectionUtils.getClass(hibernateDialect());
	}
}
