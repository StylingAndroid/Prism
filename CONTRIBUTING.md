# Contributing

We love pull requests. Here's a quick guide:

1. Check for [existing issues](https://github.com/StylingAndroid/Prism/issues) for duplicates and confirm that it hasn't been fixed already
in the [master branch](https://github.com/StylingAndroid/Prism/commits/master)
2. Fork the repo, and clone it locally
3. Create a new branch for your contribution
4. Add [tests](test/) (run with `./gradlew check`). Verify that you haven't broken any existing tests.
5. Run `./gradlew androidConnectedTest` to verify that you haven't broken any Espresso tests.
6. Check the checkstyle, findbugs. pmd, and lint reports from each sub-project and verify that you have not introduced any new warnings / errors.
8. Push to your fork and submit a pull request

At this point one of the admins will need to trigger a build of your PR on the Styling Android CI server. So you may see a comment like
'test this please' appear on GitHub. This is to trigger a CI build - you don't need to do anything!

If, after a few minutes, your PR is closed then the build has failed. Go through points 4, 5, and 6 (above) as it is likely that one of these is the
cause of the failure.

At this point you're waiting on us. We like to at least comment on, if not
accept, pull requests within a few days. We may suggest some changes or improvements or alternatives.

Some things that will increase the chance that your pull request is accepted:

* Update the documentation: code comments, example code, guides. Basically,
  update anything is affected by your contribution.
* Include any information that would be relevant to reproducing bugs, use cases for new features, etc.
* Including unit or instrumentation tests for your contribution.
* If appropriate include some documentation for a new feature.
* Your commits are associated with your GitHub user: https://help.github.com/articles/why-are-my-commits-linked-to-the-wrong-user/
* Make pull requests against a feature branch.

In a nutshell: The easier you make it for the admins to review and understand your contribution the greater likelihood of a quick acceptance -
assuming you haven't inadvertently broken anything, that is.
