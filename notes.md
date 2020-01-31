Git Notes taken from: https://www.atlassian.com/git/tutorials/inspecting-a-repository/git-tag
# Pushing to Git with tag
`git tag [tag]` to tag
`git push origin [tag]' to push to origin with tag

# Tagging Old Commits
`git tag [tag] [commit-ref]' to tag a old commit
can then push as above

# Exporting Jar
`./gradlew shadowjar` in CLT, version numbers managed in build.gradle, output files in /build/libs