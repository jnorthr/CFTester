/* ----
@Grapes([
    @Grab(group='com.h2database', module='h2', version='1.3.172'),
    @GrabConfig(systemClassLoader = true)
])
*/

import com.h2database.*
import groovy.sql.*
def lines=""

log.info "Starting to setting attribute sqltest"

log.info  "--- starting sqltest.groovy ---"
def db = Sql.newInstance('jdbc:h2:mem:', 'sa', '', 'org.h2.Driver')

// Setup database.
db.execute '''
    create table if not exists languages(
        id int primary key,
        name varchar(20) not null
    )
'''

db.execute "insert into languages values(1, 'Groovy')"
db.execute "insert into languages values(2, 'Java')"

// ------------------
// Ready for queries:
// ------------------

// Use :name syntax for named parameters.
def result = db.rows('select * from languages where name = :name', [name: 'Groovy'])
assert result[0] == [ID: 1, NAME: 'Groovy']

// simple get all example
result = db.rows('select * from languages');
//result.each{r-> println r;}

// Different syntax with ?.name.
result = db.rows('select * from languages where name = ?.name',
                 [name: 'Groovy'])
assert result[0] == [ID: 1, NAME: 'Groovy']

// We can use our own classes with properties matching
// query parameters.
class QueryParams {
    String name = 'Java'
    Integer id = 2
}

result = db.rows('select * from languages where name = ?.name and id = :id',
                 new QueryParams())
assert result[0] == [ID: 2, NAME: 'Java']


// We can use ordinal named parameters.
// For each ordinal placeholder we must pass an object
// with values.
result = db.rows('select * from languages where name = ?1.name and id = ?2.id', [name: 'Groovy'] /* ?1.name */, [id: 1] /* ?2.id */)
assert result[0] == [ID: 1, NAME: 'Groovy']

result = db.rows('select * from languages where name = ?1.name and id = ?2.id', [name: 'Groovy'], new Expando([id: 1]))
assert result[0] == [ID: 1, NAME: 'Groovy']

result = db.rows('select * from languages where name = ?1.name or name = ?2.name',new QueryParams(), [name: 'Groovy'])

def tx =  "Id is ${result[0].id.toString()} and name is ${result[0].name.toString()}\n"
log.info tx;

lines += tx;
assert result[0] == [ID: 1, NAME: 'Groovy']

tx =  "Id is ${result[1].id.toString()} and name is ${result[1].name.toString()}\n"
lines += tx
assert result[1] == [ID: 2, NAME: 'Java']

log.info  "--- ending sqltest.groovy ---"

request.setAttribute 'sqltest', lines.toString()

log.info "Forwarding to the sqltest template"

forward '/WEB-INF/pages/sqltest.gtpl'
