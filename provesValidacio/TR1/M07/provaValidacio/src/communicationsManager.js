
export async function getLibros() {
    const response = await fetch(`http://alvaro.dam.inspedralbes.cat/api.php/records/BOOK`)
    const data = await response.json()
    return data.records;
  }