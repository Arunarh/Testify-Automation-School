function countVowels(text) {
    let count = 0;

    for (let i = 0; i < text.length; i++) {
        if ("aeiou".includes(text[i].toLowerCase())) {
            count++;
        }
    }

    return count;
}
console.log(countVowels("Hello World"));