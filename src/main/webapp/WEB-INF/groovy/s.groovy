println 'vao'

def documents = search.search {
select all from motorista
where nome == "SERGE"
limit 10
}

println documents
