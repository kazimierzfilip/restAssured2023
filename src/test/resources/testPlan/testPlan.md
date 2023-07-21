# Automation Test Plan

## Introduction

Project test plan describes the approach for testing and used test processes. Establishes scope of intended testing
activities. Provides estimations for needed resources and proposes a testing schedule.

## Test Items

System under test is the application which manages blogging activities which consists of following modules:

- administration area
  - posts management
  - users management
  - blog management
- readers area
  - blog posts
  - comments

## Return on Investment (ROI)

Automation testing benefits, such as faster feedback, higher test coverage, lower human errors, and more consistent
results, require an initial investment in terms of time, money, and resources to set up and maintain the test automation
framework and scripts. And also time spent in maintaining automated test suite.

Therefore, it is necessary to calculate the return on investment (ROI) of automation testing to evaluate its
effectiveness and justify its costs. ROI is a measure of how much value is gained from an investment compared to how
much it costs. The higher the ROI, the more profitable the investment is.

ROI of automation testing can be calculated with the following formula:

`ROI = (Savings - Investment) / Investment * 100%`

Where

- Savings: The amount of money or time saved by replacing manual testing with automated testing over a certain period.
- Investment: The amount of money or time spent on building and maintaining the test automation framework and scripts
  over the same period.

## Automation test scope

In creating automation test scope ROI is used as a guideline to determine automation test coverage in a level both
supporting project development and also profitable from the resources standpoint.
This is the reason why only the area of REST API used in blog posts and comments in readers area will be covered by
automation testing.

## Testing process

- Test Planning
- Test Case Development
- Test Execution
  - Preparing scripts and data, test environment setup
  - Execution
  - Test maintenance
- Test Closure

## Test Schedule

- Test Planning: 10.07.2023 - 14.07.2023
- Test Case Development: 17.07.2023 - 21.07.2023
- Test Execution: 24.07.2023 - 11.08.2023
- Test Closure: 14.07.2023 - 15.07.2023

# Test Deliverables

- Test cases
- Automation scripts
- Test reports
- Defect reports
