let books = [
      {
    title: "Things Fall Apart",
    description: "A story about tradition and change.",
    numberOfPages: 209,
    author: "Chinua Achebe",
    reading: true
  },
    {
    title: "The Alchemist",
    description: "A story about following your dreams.",
    numberOfPages: 208,
    author: "Paulo Coelho",
    reading: false
  },
    {
    title: "Half of a Yellow Sun",
    description: "A story set during the Nigerian Civil War.",
    numberOfPages: 433,
    author: "Chimamanda Ngozi Adichie",
    reading: true
  }
];  
for (let i = 0; i < books.length; i++) {
    if (books[i].reading === true) {
        console.log(books[i]);
    }
}