package persistence.sql.ddl.query.builder;

import org.h2.table.Column;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.entity.LegacyPerson;
import persistence.entity.Person;
import persistence.sql.dialect.h2.H2ConstraintsMapper;
import persistence.sql.dialect.h2.H2TypeMapper;
import persistence.sql.entity.model.DomainType;
import persistence.sql.entity.model.EntityColumn;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnBuilderTest {

    private DomainType nameColumn;
    private DomainType emailColumn;

    @BeforeEach
    void setUp() throws Exception {


        this.nameColumn = new DomainType(new EntityColumn("name", String.class, null), LegacyPerson.class.getDeclaredField("name"), false);
        this.emailColumn = new DomainType(new EntityColumn("email", String.class, null), Person.class.getDeclaredField("email"), true);
    }

    @DisplayName("DB에 컬럼 저장할 쿼리문을 반환한다.")
    @Test
    void getColumnSql() {
        ColumnBuilder columnBuilder = new ColumnBuilder(nameColumn, H2TypeMapper.newInstance(), H2ConstraintsMapper.newInstance());

        assertThat(columnBuilder.build()).isEqualTo("name VARCHAR");
    }

    @DisplayName("Column에 있는 값으로 반환한다.")
    @Test
    void isColumnSql() {
        ColumnBuilder columnBuilder = new ColumnBuilder(emailColumn, H2TypeMapper.newInstance(), H2ConstraintsMapper.newInstance());

        assertThat(columnBuilder.build()).isEqualTo("email VARCHAR  NOT NULL");
    }
}
