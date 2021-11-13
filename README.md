# strava-joiner

Tool to join TCX files before uploading to Strava. For when you accidentally end an activity when you just meant to
pause your watch.

## Installation

Download from https://github.com/will-normand/strava-joiner

## Usage

- Supports TCX files only.
- The first file must be the earlier part of the activity, the second later.
- The output is written to `resources/output.tcx`. This should be suitable for upload to Strava.

Run the project directly:

    $ clojure -M:run-m first-file second-file

Run the project's tests:

    $ clojure -M:test

Build an uberjar:

    $ clojure -X:uberjar

Run that uberjar:

    $ java -jar strava-joiner.jar first-file second-file

## Examples

    $ clojure -M:run-m resources/activity1.tcx resources/activity2.tcx

    $ java -jar strava-joiner.jar resources/activity1.tcx resources/activity2.tcx
