build() {
    mvn package -DskipTests
}

runTests() {
    mvn test
}

commit() {
    git commit -am working
}

revert() {
    git checkout HEAD -- src/main/
}

if build; then  
    if runTests; then
        commit;
    else
        revert;
    fi
fi
